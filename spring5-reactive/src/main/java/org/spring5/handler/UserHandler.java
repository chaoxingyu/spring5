package org.spring5.handler;

import org.spring5.model.User;
import org.spring5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	@Autowired
	private UserRepository userRepository;

	
	/*
	  Mono 和 Flux 是由目标反应器提供的响应类型。Springs 还提供其他的响应流的实现，例如 RXJava。
	  Mono 和 Flux 是 Reactive streams 的发布者实现。Mono 是 0 或者任意单个值的发布，Flux 是 0 到任意值的发布。
	  他们和 RXJava 中的 Flowable 和 Observable 类似。他们代替流向这些订阅者发布信息。 
	 */
	public Mono<ServerResponse> handleGetUsers(ServerRequest request) {
		return ServerResponse.ok().body(userRepository.getUsers(), User.class);
	}

	public Mono<ServerResponse> handleGetUserById(ServerRequest request) {
		return userRepository.getUserById(request.pathVariable("id"))
				.flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
				.switchIfEmpty(ServerResponse.notFound().build());
		/*
		 GetUserById() 返回一个 Mono<User> 对象，这个对象不论何时都会返回 0~1 个用户对象，
		 GetUsers() 返回一连串变动的用户对象，不论何时都包含 0~n 个用户对象。
		
		 相比命令式编程风格，我们并不返回可用前阻塞线程的 User/List<User> 对象，
		 而只是返回一个流的引用，流可以在后面访问 User/List<User>。

		 */
	}

}
