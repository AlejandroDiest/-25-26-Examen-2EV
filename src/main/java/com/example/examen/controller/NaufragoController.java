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

import com.example.examen.entity.Naufrago;
import com.example.examen.service.NaufragoService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/naufragos")
public class NaufragoController {


    @Autowired
    private NaufragoService naufragoService;

  
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("naufragos", naufragoService.listarTodos());
        return "naufragos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("Naufrago", new Naufrago());
        return "naufragos/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("naufrago") Naufrago naufrago,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "naufragos/form";
        }
        
      
        boolean esNuevo = naufrago.getId() == null;
        naufragoService.guardar(naufrago);
        

        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensajeExito",
                "Naufrago '" + naufrago.getNombre() + "' creado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito",
                "Naufrago '" + naufrago.getNombre() + "' actualizado correctamente.");
        }
        
        return "redirect:/naufragos";
    }


   
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Naufrago naufrago = naufragoService.buscarPorId(id);
        String nombreNaufrago = naufrago.getNombre();
        naufragoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito",
            "Naufrago '" + nombreNaufrago + "' eliminado correctamente.");
        return "redirect:/naufragos";
    }
}
