package com.devshawn.coloring.server.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class EnableHTML5Controller {

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/"
    }

    @RequestMapping(value = "/{[path:[^\\.]*}/{[id:[^\\.]*}")
    public String redirect2() {
        return "forward:/"
    }
}