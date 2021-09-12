package com.harrybro.simplechatting;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    // show databases
    // use <database name>
    // db.runCommand({convertToCapped: 'chat', size: 8172}) 설정해줘야 에러 발생 안됨. chat은 collection name
    @Tailable
    Flux<Chat> findBySenderAndReceiver(String sender, String receiver);

}
