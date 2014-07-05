package com.letter.controllers;

import com.letter.domain.AdItem;
import com.letter.domain.Order;
import com.letter.exception.IdException;
import com.letter.helpers.JspHelper;
import com.letter.model.JsonResponse;
import com.letter.model.OrderRequest;
import com.letter.service.AdService;
import com.letter.service.OrderService;
import com.letter.validator.OrderRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping(value = { "/", "/ad" })
public class AdController {


    private static final Logger log = LoggerFactory.getLogger(AdController.class);

    @Autowired
    private List<ViewResolver> viewResolver;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AdService adService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("itemList", adService.getAll());
        return new ModelAndView ( "ad/list", model );
    }

    private Locale getLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(" \t\r\n\f", true));
    }

/*
    @InitBinder
    private void initBinder(WebDataBinder binder, final Locale locale) {
        binder.setValidator(new OrderRequestValidator(locale));
    }*/

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody JsonResponse update(@Valid AdItem item,
                                             BindingResult bindingResult,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return new JsonResponse(
                    bindingResult,
                    getLocale(request),
                    messageSource);
        }
        try {
            adService.update(item);
        } catch (IdException e) {
            JsonResponse result = new JsonResponse();
            result.addError("num", messageSource.getMessage("num.duplicate", new Object[]{}, getLocale(request)));
            return result;
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("itemList", adService.getAll());

        return new JsonResponse(new JspHelper().toString(new ModelAndView("ad/_tablePartial", model), request, response, viewResolver.get(0)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody JsonResponse delete(long id,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        adService.remove(id);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("itemList", adService.getAll());
        return new JsonResponse(new JspHelper().toString(new ModelAndView("ad/_tablePartial", model), request, response, viewResolver.get(0)));
    }

    /*

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResponse publish(@RequestParam("id") long id) {
        orderService.publish(id);
        return new JsonResponse();
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") long id, HttpServletResponse response) throws Exception {
        Order order = orderService.get(id);

        response.setContentType(order.getScanType());
        response.setContentLength(order.getOrderScan().getScan().length);

        response.setHeader("content-Disposition",
                String.format("attachment; filename=file_%s.%s", order.getId(), order.getExt())
        );

        try {
            response.getOutputStream().write(order.getOrderScan().getScan());
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("download", e);
            throw (e);
        }
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public
    @ResponseBody
    JsonResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        JsonResponse response = new JsonResponse();
        response.addError("scanFile", messageSource.getMessage("scanFile.error.tooMach", new Object[]{}, getLocale(request)));
        return response;

    }*/

}
