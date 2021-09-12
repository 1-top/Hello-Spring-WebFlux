package com.harrybro.simplechatting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatApiController {

    private final ChatRepository chatRepository;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessage(@RequestParam String sender, @RequestParam String receiver) {
        return this.chatRepository.findBySenderAndReceiver(sender, receiver).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping
    public ResponseEntity<Mono<Chat>> sendMessage(@RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(this.chatRepository.save(chatDto.toEntity()));
    }

}
