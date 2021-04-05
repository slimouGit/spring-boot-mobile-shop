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

    @GetMapping(path = "/view/{id}")
    public String viewContact(Model model, @PathVariable Long id) {
        Optional<Mobil> mobil = mobilRepository.findById(id);
        if (mobil.isPresent()) {
            model.addAttribute("mobil", mobil.get());
            model.addAttribute("name", mobil.get().getName());
            model.addAttribute("description", mobil.get().getDescription());
            model.addAttribute("price", mobil.get().getPrice());
            model.addAttribute("image", mobil.get().getImage());

        } else {
            return "/store";
        }
        return "mobil-detail";
    }

    @RequestMapping("/contact-detail")
    public String detailansicht(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", mobilRepository.findById(id).get().getId());
        model.addAttribute("name", mobilRepository.findById(id).get().getName());
        model.addAttribute("description", mobilRepository.findById(id).get().getDescription());
        model.addAttribute("price", mobilRepository.findById(id).get().getPrice());
        model.addAttribute("image", mobilRepository.findById(id).get().getName());
       return "mobil-detail";
    }


}
