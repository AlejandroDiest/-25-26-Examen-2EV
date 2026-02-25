package com.example.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.examen.entity.Habilidad;
import com.example.examen.service.HabilidadService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/habilidades")
public class HabilidadController {


    @Autowired
    private HabilidadService habilidadService;

  
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("habilidades", habilidadService.listarTodos());
        return "habilidades/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("Habilidad", new Habilidad());
        return "habilidades/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("habilidad") Habilidad habilidad,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "habilidades/form";
        }
        
      
        boolean esNuevo = habilidad.getId() == null;
        habilidadService.guardar(habilidad);
        

        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensajeExito",
                "Habilidad '" + habilidad.getNombre() + "' creado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito",
                "Habilidad '" + habilidad.getNombre() + "' actualizado correctamente.");
        }
        
        return "redirect:/habilidades";
    }


   
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Habilidad habilidad = habilidadService.buscarPorId(id);
        String nombreNaufrago = habilidad.getNombre();
        habilidadService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito",
            "Habilidad '" + nombreNaufrago + "' eliminado correctamente.");
        return "redirect:/habilidades";
    }
}
