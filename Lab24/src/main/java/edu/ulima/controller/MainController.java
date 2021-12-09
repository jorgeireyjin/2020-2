
package edu.ulima.controller;

import edu.ulima.entidad.Producto;
import edu.ulima.modelo.ProductInfo;
import edu.ulima.persistencia.ProductoRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private ProductoRepositorio repo;
    
    @RequestMapping(value="/")
    public String home() {
        return "index";
    }
    
    @RequestMapping({"/listarProductos"})
    public String listarProductos(Model model,
            @RequestParam(value="page", defaultValue="1")  int page) {
        
        // -------------- Inicio algoritmos de paginacion
final int maxResult = 5;                  // Cantidad de resultados por pagina
        final int maxNavigationPage = 10; // cantidad maxima de paginas

        final int pageIndex = page - 1 < 0 ? 0 : page - 1;

        // Cuantos resultados existen en total
        int totalRecords = repo.findAll().size();

        // calcular cuantas paginas se debe tener
        int totalPages = 0;
        if (totalRecords % maxResult == 0) {
            totalPages = totalRecords / maxResult;
        } else {
            totalPages = (totalRecords / maxResult) + 1;
        }

        int currentPage = pageIndex + 1;

        List<Integer> navigationPages = new ArrayList<>();
        int current = currentPage > totalPages ? totalPages : currentPage;

        int begin = current - maxNavigationPage / 2;
        int end = current + maxNavigationPage / 2;

        // La primera pagina
        navigationPages.add(1);
        if (begin > 2) {
            navigationPages.add(-1);
        }

        // Llenar un arreglo con los numero de paginas
        for (int i = begin; i < end; i++) {
            if (i > 1 && i < totalPages) {
                System.out.println("En navigationPages.add " + i );
                navigationPages.add(i);
            }
        }

        if (end < totalPages - 2) {
            navigationPages.add(-1);
        }

        navigationPages.add(totalPages);
        // -------------- Fin algoritmos de paginacion
        
        /*
        Ir a la base de datos pero ..a buscar una pagina de datos
        */
        Pageable pagina = PageRequest.of(pageIndex, maxResult);
        // Invocar al repositorio para que retorne la pagina indicada
        Page<Producto> result0 = repo.findAll(pagina);
        
        // Convertir la lista de Entidad a Modelo
        List<ProductInfo> ltmp = new ArrayList<>(); 
        for (Producto p: result0) {
            ProductInfo pi = new ProductInfo(p.getCodigo(), p.getNombre(), p.getPrecio());
            ltmp.add(pi);
        }
        
        // Generar una pagina
        Page<ProductInfo> result = new PageImpl(ltmp);
        
        // POner todo en el modelo
        model.addAttribute("paginationProducts", result);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("navigationPages", navigationPages);
        
        return "productoLista";
    }
    
    @RequestMapping(value = {"/productoImagen"}, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, 
                            HttpServletResponse response,
                            @RequestParam("code") String code) throws IOException {
        Producto p = null;
        if (code != null) {
            p = repo.findByCodigo(code);
        }
        
        if (p != null && p.getImagen() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(p.getImagen());
        }
        
        response.getOutputStream().close();
    }


}
