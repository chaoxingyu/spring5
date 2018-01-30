package org.spring5.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.spring5.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class Routes {
	
	private UserHandler userHandler;

	public Routes(UserHandler userHandler) {
		this.userHandler = userHandler;
	}
	
	@Bean
	public RouterFunction<?> routerFunction() {
		return
				route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)), userHandler::handleGetUsers)
				.and(route(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::handleGetUserById));
//				.and(route(POST("/users"), userHandler::handleGetUsers));
		
	/*
	 * 定义应用程序路由的路由类
	 
	 RouterFunction就像Spring Web中的@RequestMapping类一样。 
	 RouterFunction用于定义Spring5应用程序的路由。 
	 RouterFunctions帮助器类有一个有用的方法，类似路由，可用于定义路由并构建RouterFunction对象。 
	 RequestPredicates有许多有用的方法，如GET，POST，path，queryParam，accept，headers，contentType等，来定义路由并构建RouterFunction。 每个路由映射到一个处理程序方法，当接收到适当的HttpRequest时，该方法必须被调用。
	 Spring5还支持定义应用程序处理程序映射的@RequestMapping类型的控制器。 
	 我们可以编写如下所示的控制器方法，以在@RequestMapping样式中创建类似的API。
	 	@GetMapping("/user") public Mono<ServerResponse> handleGetUsers() {}
	    控制器方法返回Mono<ServerResponse>。

	 RouterFunction为应用程序提供了DSL类型的路由功能。 到目前为止，Springs不支持混合这两种类型。
	 */
	}

}
