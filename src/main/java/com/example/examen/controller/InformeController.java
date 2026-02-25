package com.example.examen.controller;

import com.example.examen.entity.Naufrago;
import com.example.examen.service.NaufragoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/informes")
public class InformeController {

    @Autowired
    private NaufragoService naufragoService;

    @GetMapping
    public String verGraficos(Model model) {
        List<Naufrago> lista = naufragoService.listarTodos();

        Map<String, Long> nacMap = lista.stream()
            .collect(Collectors.groupingBy(n -> n.getNacionalidad() != null ? n.getNacionalidad() : "N/A", Collectors.counting()));

        Map<String, Long> sexoMap = lista.stream()
            .collect(Collectors.groupingBy(n -> n.getSexo() != null ? n.getSexo() : "Otro", Collectors.counting()));

        model.addAttribute("nacionalidadLabels", nacMap.keySet());
        model.addAttribute("nacionalidadData", nacMap.values());
        
        model.addAttribute("sexoLabels", sexoMap.keySet());
        model.addAttribute("sexoData", sexoMap.values());

        return "informes/graficos";
    }
}