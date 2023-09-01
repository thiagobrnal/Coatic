package com.coatic.coatic.controladores;

import com.coatic.coatic.entidades.*;
import com.coatic.coatic.service.*;
import com.coatic.coatic.repository.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("areas")
public class AreaController implements WebMvcConfigurer {
    
    @Autowired
    AreaService areaService;

    @Autowired
    AreaRepository areaRepository;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Listado de áreas");
        mvc.addObject("vista", "areas/index");
        mvc.addObject("areas", areaService.getAll());
        return mvc;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Area area){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Crear área");
        mvc.addObject("vista", "areas/crear");
        mvc.addObject("area", area);
        return mvc;
    }

    @PostMapping("/crear")
    public ModelAndView guardar(@Valid Area area, BindingResult br, RedirectAttributes ra){
        if(br.hasErrors()){
            return this.crear(area);
        }

        areaService.save(area);

        ModelAndView mvc = this.index();
        mvc.addObject("exito", "Área creada exitosamente");
        return mvc;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, Area area){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Editar área");
        mvc.addObject("vista", "areas/editar");
        mvc.addObject("area", areaService.getById(id));
        return mvc;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView actualizar(@PathVariable("id") Long id, @Valid Area area, BindingResult br, RedirectAttributes ra) {
        if ( br.hasErrors() ) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("fragments/base");
            mav.addObject("titulo", "Editar área");
            mav.addObject("vista", "areas/editar");
            mav.addObject("area", area);
            return mav;
        }

        Area registro = areaService.getById(id);
        registro.setNombre(area.getNombre());
        ModelAndView mav = this.index();

        areaService.save(registro);
        mav.addObject("exito", "Área editada exitosamente");
        return mav;
    }

    @DeleteMapping("/{id}")
    public ModelAndView eliminar(@PathVariable("id") Long id){
     ModelAndView mav;

     if( areaRepository.hasReferences(id) ) {
        mav = this.index();
        mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
     } else {
        areaService.delete(id);
        mav = this.index();
        mav.addObject("exito", "Área eliminada exitosamente");
        
     }
        return mav;

    }

}
