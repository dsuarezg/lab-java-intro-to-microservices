package com.ironhack.student_catalog_service.controllers;

import com.ironhack.student_catalog_service.models.Catalog;
import com.ironhack.student_catalog_service.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    /**
     * Handles GET requests for retrieving a catalog by course code.
     *
     * Returns the catalog associated with the specified course code if found, or a 404 Not Found response if not.
     * Responds with 502 Bad Gateway in case of processing errors.
     *
     * @param courseCode the course code used to look up the catalog
     * @return HTTP 200 with the catalog if found, 404 if not found, or 502 on error
     */
    @GetMapping("/{courseCode}")
    public ResponseEntity<?> getCatalog(@PathVariable Long courseCode) {
        try {
            Catalog catalog = catalogService.getCatalogByCourseCode(courseCode);
            System.out.println(catalog);
            return (catalog != null)
                    ? ResponseEntity.ok(catalog)
                    : ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(502).build();
        }
    }
}