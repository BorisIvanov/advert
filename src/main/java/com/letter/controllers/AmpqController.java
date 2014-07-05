package com.letter.controllers;

import com.letter.domain.AdItem;
import com.letter.exception.IdException;
import com.letter.helpers.JspHelper;
import com.letter.model.JsonResponse;
import com.letter.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping(value = { "/ampq" })
public class AmpqController {


    private static final Logger log = LoggerFactory.getLogger(AmpqController.class);

    @Autowired
    private List<ViewResolver> viewResolver;

    @Autowired
    private MessageSource messageSource;
/*
    @Autowired
    private AdService adService;
*/
    @Autowired private AmqpAdmin amqpAdmin;
    @Autowired private AmqpTemplate amqpTemplate;
    @Autowired private Queue rabbitQueue;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        amqpAdmin.declareQueue(new Queue(rabbitQueue.getName()));
        Map<String, Object> model = new HashMap<>();
        //model.put("itemList", adService.getAll());
        amqpTemplate.convertAndSend(rabbitQueue.getName(), "Message 1");
        return new ModelAndView ( "ad/list", model );
    }

}
