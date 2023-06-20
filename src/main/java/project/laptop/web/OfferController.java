package project.laptop.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laptop.exceptions.ObjectNotFoundException;
import project.laptop.model.dto.offerDTO.CreateOrUpdateOfferDto;
import project.laptop.model.dto.offerDTO.EditOfferDto;
import project.laptop.model.dto.offerDTO.SearchOfferDto;
import project.laptop.service.BrandService;
import project.laptop.service.OfferService;

import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new CreateOrUpdateOfferDto());
        }
        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid CreateOrUpdateOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        MultipartFile imageFile = addOfferModel.getImageUrl();
        offerService.addOffer(addOfferModel, userDetails,imageFile);

        return "redirect:all";

    }

    @GetMapping("/all")
    public String allOffers(Model model,
                            @PageableDefault(page = 0, size = 1, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("offers", offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/{id}")
    public String getOfferDetails(@PathVariable("id") Long id, Model model) {

        var offerDto = offerService.findOfferById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found"));

        model.addAttribute("isOwner",
                offerService.isOwner(SecurityContextHolder.getContext().getAuthentication().getName(), id));
        //check if its owner it html
        model.addAttribute("offer", offerDto);

        return "details";
    }

    @PreAuthorize("@offerService.isOwner(#principal.name,#id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(Principal principal, @PathVariable("id") Long id) {
        offerService.deleteOfferById(id);

        return "redirect:all";
    }


    @PreAuthorize("@offerService.isOwner(#principal.name,#id)")
    @GetMapping("/{id}/edit")
    public String edit(Principal principal, @PathVariable("id") Long id, Model model) {
        var offer = offerService.findOfferById(id).orElseThrow(() -> new ObjectNotFoundException("Offer with ID " + id + "not found"));

        if (!model.containsAttribute("editOfferDto")) {
            model.addAttribute("editOfferDto", new EditOfferDto());
        }
        model.addAttribute("offer", offer);

        return "update";

    }

    @PostMapping("/{id}/edit")
    public String editConfirm(@PathVariable("id") Long id,
                              @Valid EditOfferDto editOfferDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editOfferDto", editOfferDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editOfferDto", bindingResult);

            return "redirect:/offers/{id}/edit";
        }


        offerService.edit(id, editOfferDto);
        return "redirect:/offers/" + id;
    }


    @GetMapping("/search")
    public String searchQuery(@Valid SearchOfferDto searchOfferDto,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDto);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "offer-search";
        }

        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDto);
        }

        if (!searchOfferDto.isEmpty()) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDto));
        }


        return "offer-search";
    }


}
