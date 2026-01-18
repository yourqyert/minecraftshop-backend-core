package labs.prism.webcore.controller;

import labs.prism.webcore.domain.entity.Delivery;
import labs.prism.webcore.repository.ServerRepository;
import labs.prism.webcore.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plugin")
@RequiredArgsConstructor
public class PluginApiController {

    private final DeliveryService deliveryService;
    private final ServerRepository serverRepository;

    /**
     * Плагин запрашивает список команд для выполнения.
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestHeader("X-Server-Key") String secretKey) {
        // Простая проверка безопасности
        if (serverRepository.findBySecretKey(secretKey).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<TaskDto> tasks = deliveryService.getPendingDeliveries().stream()
                .map(d -> new TaskDto(d.getId(), d.getCommand()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    /**
     * Плагин подтверждает, что команда выполнена успешно.
     */
    @PostMapping("/complete/{id}")
    public ResponseEntity<Void> completeTask(
            @PathVariable Long id,
            @RequestHeader("X-Server-Key") String secretKey) {

        if (serverRepository.findBySecretKey(secretKey).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        deliveryService.markAsDone(id);
        return ResponseEntity.ok().build();
    }

    /**
     * DTO для передачи данных плагину.
     */
    public record TaskDto(Long id, String command) {}
}