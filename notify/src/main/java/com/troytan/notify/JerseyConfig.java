package com.troytan.notify;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.troytan.notify.controller.CommentController;
import com.troytan.notify.controller.NotifyController;
import com.troytan.notify.controller.UserController;
import com.troytan.notify.filter.JacksonObjectMapperProvider;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(CommentController.class);
        register(NotifyController.class);
        register(UserController.class);

        register(JacksonJaxbJsonProvider.class);
        register(JacksonObjectMapperProvider.class);
    }
}
