package com.coatic.coatic.controladores;


import com.coatic.coatic.entidades.*;
import com.coatic.coatic.service.*;


import java.io.File;
import java.nio.file.Paths;                             //import para imagenes
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("cursos")
public class CursoController implements WebMvcConfigurer {
    
    @Autowired
    AreaService areaService;

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Listado de cursos");
        mvc.addObject("vista", "cursos/index");
        mvc.addObject("cursos", cursoService.getAll());
        return mvc;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Curso curso){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Crear cursos");
        mvc.addObject("vista", "cursos/crear");
        mvc.addObject("curso", curso);
        mvc.addObject("areas", areaService.getAll());
        return mvc;
    }

    @PostMapping("/crear")
    public ModelAndView guardar(@RequestParam("archivo") MultipartFile archivo, @Valid Curso curso, BindingResult br, RedirectAttributes ra) {
        if ( archivo.isEmpty() ){
            br.reject("archivo", "Por favor, cargue una imagen");
        }
        if ( br.hasErrors() ) {
            return this.crear(curso);
        }
        cursoService.save(curso);

        //Guarda la imagen con nombre (ID) y la extension
        String tipo = archivo.getContentType();
        String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
        String imagen = curso.getId() + extension;
        String path = Paths.get("src/main/resources/static/images/cursos", imagen).toAbsolutePath().toString();
        ModelAndView mav = this.index();

        try {
            archivo.transferTo( new File(path) );
        } catch (Exception e) {
            mav.addObject("error", "No se pudo guardar la imagen");
            return mav;
        }

        curso.setImagen(imagen);
        cursoService.save(curso);
        mav.addObject("exito", "Curso creado exitosamente");
        return mav;
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, Curso curso){

        return this.editar(id, curso, true);
    }

    public ModelAndView editar(@PathVariable("id") Long id, Curso curso, boolean estaPersistido){
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("fragments/base");
        mvc.addObject("titulo", "Editar curso");
        mvc.addObject("vista", "cursos/editar");
        mvc.addObject("curso", cursoService.getById(id));
        mvc.addObject("areas", areaService.getAll());

        if(estaPersistido){
            mvc.addObject("curso", cursoService.getById(id));
        }else{
            curso.setImagen( cursoService.getById(id).getImagen());
        }

        return mvc;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView update(@PathVariable("id") Long id, @RequestParam(value = "archivo", required = false) MultipartFile archivo, @Valid Curso curso, BindingResult br, RedirectAttributes ra) {
        if ( br.hasErrors() ) {
            return this.editar(id, curso, false);
        }

        Curso registro = cursoService.getById(id);
        registro.setNombre( curso.getNombre() );
        registro.setMeses( curso.getMeses() );
        registro.setInicio( curso.getInicio() );
        registro.setPresencial( curso.isPresencial() );
        registro.setArea( curso.getArea() );
        ModelAndView maw = this.index();

        if ( ! archivo.isEmpty() ) {
            String tipo = archivo.getContentType();
            String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
            String imagen = curso.getId() + extension;
            String path = Paths.get("src/main/resources/static/images/cursos", imagen).toAbsolutePath().toString();

            try {
                archivo.transferTo( new File(path) );
            } catch (Exception e) {
                maw.addObject("error", "No se pudo guardar la imagen");
                return maw;
            }

            registro.setImagen(imagen);
        }

        cursoService.save(registro);
        maw.addObject("exito", "Curso editado exitosamente");
        return maw;
    }

    @DeleteMapping("/{id}")
    public ModelAndView eliminar(@PathVariable("id") Long id) {
        cursoService.delete(id);
        ModelAndView mav = this.index();
        mav.addObject("exito", "Curso eliminado exitosamente");
        return mav;
    }

}

