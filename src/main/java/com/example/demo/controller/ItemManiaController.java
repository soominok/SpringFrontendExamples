package com.example.demo.controller;

import com.example.demo.entity.ItemMania;
import com.example.demo.service.ItemManiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemManiaController {
    public static final Logger log =
            LoggerFactory.getLogger(ItemManiaController.class);

    @Autowired
    private ItemManiaService service;

    @GetMapping("/getItemManiaRegister")
    public String getItemManiaRegister(ItemMania itemMania, Model model)
            throws Exception {

        log.info("getItemManiaRegister()");

        return "itemmania/register";
    }

    @PostMapping("/postItemManiaRegister")
    public String postItemManiaRegister(ItemMania itemMania, Model model)
            throws Exception {
        log.info("postItemManiaRegister()");

        service.register(itemMania);

        model.addAttribute("msg","Register Success");

        return "itemmania/success";
    }

    @GetMapping("/login")
    public String login(ItemMania itemMania, Model model) throws Exception {

        log.info("login()");

        return "itemmania/login";
    }

    @GetMapping("/itemlist")
    public String list(Model model) throws Exception {
        log.info("itemlist()");

        model.addAttribute("itemlist", service.list());

        return "itemmania/list";
    }

    @PostMapping("/itemremove")
    public String remove(int itemmaniaNo, Model model)
            throws Exception {
        // 현재 remove가 잘 실행되고 있다는 것을 알려줌.
        log.info("itemremove()");

        service.remove(itemmaniaNo);

        model.addAttribute("msg", "Success Delete!");

        return "itemmania/success";
    }
}
