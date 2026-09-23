package com.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Tur;
import com.service.CinsService;
import com.service.TurService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class TurController {

    @Autowired
    TurService turService;

    @Autowired
    CinsService cinsService;

    @RequestMapping(value = "/tur", method = RequestMethod.GET)
    public ModelAndView showTurList() {
        ModelAndView model = new ModelAndView();
        model.addObject("cinsList", cinsService.listCins());
        model.addObject("turList", turService.listTur());
        model.setViewName("tur");
        return model;
    }

    @RequestMapping(value = "/turFiltrele", method = RequestMethod.GET)
    public ModelAndView showTurListFiltre(@ModelAttribute(value="cinsid") String cinsid) {
        ModelAndView model = new ModelAndView();
        List<Tur> l = null;
        if(cinsid.equals("")) {
            l = turService.listTur();
        } else {
            l = turService.listTur(cinsid);
        }
        model.addObject("cinsList", cinsService.listCins());
        model.addObject("turList", l);
        model.setViewName("tur");
        return model;
    }

    // --- YENİ EKLENEN TÜR RAPOR METODU ---
    @RequestMapping(value = "/turRapor", method = RequestMethod.GET)
    public void generateTurReport(HttpServletResponse response) {
        try {
            List<Tur> turList = turService.listTur();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(turList);

            InputStream reportStream = getClass().getResourceAsStream("/com/reports/agac_tur.jasper");
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, new HashMap<>(), dataSource);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=tur_raporu.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/silTur/{id}", method = RequestMethod.GET)
    public String deleteFromTurList(@PathVariable String id) {
        Tur tur = turService.findTurById(id);
        turService.delete(tur);
        return "redirect:/tur";
    }

    @RequestMapping(value = "/turKaydet", method = RequestMethod.POST)
    public String saveNewTur(Tur tur) {
        turService.add(tur);
        return "redirect:/tur";
    }

    @RequestMapping(value = "/turGuncelle", method = RequestMethod.POST)
    public String editCurrentTurButton(Tur tur) {
        turService.update(tur);
        return "redirect:/tur";
    }
}