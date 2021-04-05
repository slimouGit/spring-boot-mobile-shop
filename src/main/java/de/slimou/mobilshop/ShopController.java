package de.slimou.mobilshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
public class ShopController {

    private MobilRepository mobilRepository;

    public ShopController(MobilRepository mobilRepository) {
        this.mobilRepository = mobilRepository;
    }

    @GetMapping(path = "/home")
    public String homepage() {
        return "index";
    }

    /**
     * Pfad fuer Bildausgabe und Formate definieren
     */
    @GetMapping("/image/display/{id}")
    @ResponseBody
    void dshowMobiles(@PathVariable("id") Long id, HttpServletResponse response, Optional<Mobil> mobil)
            throws ServletException, IOException {
        mobil = mobilRepository.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(mobil.get().getImage());
        response.getOutputStream().close();
    }

    /**
     * Anzeigen aller Mobil-Objekte
     */
    @GetMapping(path = "/store")
    public String showMobiles(Model model) {
        List<Mobil> mobils = mobilRepository.findAll();
        model.addAttribute("mobil", mobils);
        return "mobil-store";
    }

    /**
     * Eingabe-Maske
     */
    @GetMapping(path = "/new")
    public String addImage(Model model) {
        Mobil mobil = new Mobil();
        model.addAttribute("mobil", mobil);
        return "add-mobil";
    }

    /**
     * Formular-Eingaben entgegen nehmen und in Mobil speichern
     */
    @PostMapping(path = "/new")
    public String saveImage(@RequestParam("image") MultipartFile file, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") Double price) {
        Mobil mobil = new Mobil();
        mobil.setName(name);
        mobil.setDescription(description);
        mobil.setPrice(price);
        try {
            mobil.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobilRepository.save(mobil);
        return "redirect:/store";
    }


}
