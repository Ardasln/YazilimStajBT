package com.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Cins;
import com.service.CinsService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class CinsController {

    @Autowired
    CinsService cinsService;
    
    @RequestMapping(value = "/cinsRapor", method = RequestMethod.GET)
    public void generateCinsReport(HttpServletResponse response) {
        try {
            // 1. Verileri MongoDB'den çek
            List<Cins> cinsList = cinsService.listCins();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cinsList);

            // 2. .jasper şablon dosyasını oku (com/reports klasöründeki konuma göre)
            InputStream reportStream = getClass().getResourceAsStream("/com/reports/agac_cins.jasper");
            
            // 3. Raporu verilerle doldur
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, new HashMap<>(), dataSource);

            // 4. HTTP yanıt başlıklarını PDF olarak ayarla
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=cins_raporu.pdf");

            // 5. PDF akışını tarayıcıya aktar
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/cins", method = RequestMethod.GET)
    public ModelAndView showCinsList() {
        ModelAndView model = new ModelAndView();
        model.addObject("cinsList", cinsService.listCins());
        model.setViewName("cins");
        return model;
    }

    @RequestMapping(value = "/silCins/{id}", method = RequestMethod.GET)
    public String deleteFromCinsList(@PathVariable String id) {
        Cins cins = cinsService.findCinsById(id);
        cinsService.delete(cins);
        return "redirect:/cins";
    }

    @RequestMapping(value = "/cinskaydet", method = RequestMethod.POST)
    public String saveNewCins(Cins cins) {
        cinsService.add(cins);
        return "redirect:/cins";
    }

    @RequestMapping(value = "/cinsGuncelle", method = RequestMethod.POST)
    public String editCurrentCinsButton(Cins cins) {
        cinsService.update(cins);
        return "redirect:/cins";
    }
}