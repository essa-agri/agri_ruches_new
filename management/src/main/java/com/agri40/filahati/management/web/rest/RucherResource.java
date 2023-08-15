package com.agri40.filahati.management.web.rest;

import com.agri40.filahati.management.domain.Ruche;
import com.agri40.filahati.management.domain.Rucher;
import com.agri40.filahati.management.repository.RucherRepository;
import com.agri40.filahati.management.service.RucheService;
import com.agri40.filahati.management.service.RucherService;
import com.agri40.filahati.management.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.agri40.filahati.management.domain.Rucher}.
 */
@RestController
@RequestMapping("/api")
public class RucherResource {

    private final Logger log = LoggerFactory.getLogger(RucherResource.class);

    private static final String ENTITY_NAME = "managementRucher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RucherService rucherService;
    private final RucheService rucheService;

    private final RucherRepository rucherRepository;

    public RucherResource(RucherService rucherService, RucheService rucheService, RucherRepository rucherRepository) {
        this.rucherService = rucherService;
        this.rucheService = rucheService;
        this.rucherRepository = rucherRepository;
    }

    /**
     * {@code POST  /ruchers} : Create a new rucher.
     *
     * @param rucher the rucher to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rucher, or with status {@code 400 (Bad Request)} if the rucher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ruchers")
    public ResponseEntity<Rucher> createRucher(@Valid @RequestBody Rucher rucher) throws URISyntaxException {
        log.debug("REST request to save Rucher : {}", rucher);
        if (rucher.getId() != null) {
            throw new BadRequestAlertException("A new rucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Rucher result = rucherService.save(rucher);
        return ResponseEntity
            .created(new URI("/api/ruchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ruchers/:id} : Updates an existing rucher.
     *
     * @param id the id of the rucher to save.
     * @param rucher the rucher to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rucher,
     * or with status {@code 400 (Bad Request)} if the rucher is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rucher couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ruchers/{id}")
    public ResponseEntity<Rucher> updateRucher(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Rucher rucher
    ) throws URISyntaxException {
        log.debug("REST request to update Rucher : {}, {}", id, rucher);
        if (rucher.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rucher.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rucherRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Rucher result = rucherService.update(rucher);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rucher.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /ruchers/:id} : Partial updates given fields of an existing rucher, field will ignore if it is null
     *
     * @param id the id of the rucher to save.
     * @param rucher the rucher to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rucher,
     * or with status {@code 400 (Bad Request)} if the rucher is not valid,
     * or with status {@code 404 (Not Found)} if the rucher is not found,
     * or with status {@code 500 (Internal Server Error)} if the rucher couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ruchers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Rucher> partialUpdateRucher(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Rucher rucher
    ) throws URISyntaxException {
        log.debug("REST request to partial update Rucher partially : {}, {}", id, rucher);
        if (rucher.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rucher.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rucherRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Rucher> result = rucherService.partialUpdate(rucher);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rucher.getId()));
    }

    /**
     * {@code GET  /ruchers} : get all the ruchers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ruchers in body.
     */
    @GetMapping("/ruchers")
    public List<Rucher> getAllRuchers() {
        log.debug("REST request to get all Ruchers");
        return rucherService.findAll();
    }

    /**
     * {@code GET  /ruchers/:id} : get the "id" rucher.
     *
     * @param id the id of the rucher to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rucher, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ruchers/{id}")
    public ResponseEntity<Rucher> getRucher(@PathVariable String id) {
        log.debug("REST request to get Rucher : {}", id);
        Optional<Rucher> rucher = rucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rucher);
    }

    @GetMapping("/ruchers/ListRuchesById/{id}")
    public ResponseEntity<Rucher> getListRuchesById(@PathVariable String id) {
        log.debug("REST request to get Rucher : {}", id);
        Optional<Rucher> rucher = rucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rucher);
    }

    /**
     * {@code DELETE  /ruchers/:id} : delete the "id" rucher.
     *
     * @param id the id of the rucher to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ruchers/{id}")
    public ResponseEntity<Void> deleteRucher(@PathVariable String id) {
        log.debug("REST request to delete Rucher : {}", id);

        rucheService.deleteByRucherId(id);
        rucherService.delete(id);

        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    //
    @GetMapping("/ruchers/{user_id}/count")
    public int getCountRechersByUserId(@PathVariable String user_id) {
        return rucherService.getCountRechersByUserId(user_id);
    }

    //
    @GetMapping("/ruchers/{id}/listRuchesCount")
    public int getCountListRuchesById(@PathVariable String id) {
        return rucherService.getListRuchesCountById(id);
    }

    //
    @GetMapping("/rucher/{user_id}/listRuchesCount")
    public int getListRuchesCountByUserId(@PathVariable String user_id) {
        return rucherService.getListRuchesCountByUserId(user_id);
    }

    //
    @GetMapping("/ruchesByUserId/{user_id}")
    public List<Ruche> getListRuchesByUserId(@PathVariable String user_id) {
        return rucherService.getListRuchesByUserId(user_id);
    }

    @GetMapping("/ruchersByUserId/{user_id}")
    public List<Rucher> getRuchersByUserId(@PathVariable String user_id) {
        return rucherService.getRuchersByUserId(user_id);
    }

    // Endpoint to add a Ruche to a Rucher
    @PostMapping("/{rucherId}/addRuche")
    public ResponseEntity<Rucher> addRucheToRucher(@PathVariable String rucherId, @RequestParam String rucheId) {
        Rucher updatedRucher = rucherService.addRucheToRucher(rucherId, rucheId);
        if (updatedRucher != null) {
            return ResponseEntity.ok(updatedRucher);
        } else {
            // Handle the case when the Rucher or Ruche with the given IDs is not found.
            // You may return a not found response (HttpStatus.NOT_FOUND) or an error message.
            return ResponseEntity.notFound().build();
        }
    }

    //
    // Endpoint to delete a Ruche from a Rucher
    @DeleteMapping("/{rucherId}/deleteRuche")
    public ResponseEntity<Rucher> deleteRucheInListRuches(@PathVariable String rucherId, @RequestParam String rucheId) {
        Rucher updatedRucher = rucherService.deleteRucheInListRuches(rucherId, rucheId);
        if (updatedRucher != null) {
            return ResponseEntity.ok(updatedRucher);
        } else {
            // Handle the case when the Rucher with the given ID is not found.
            // You may return a not found response (HttpStatus.NOT_FOUND) or an error message.
            return ResponseEntity.notFound().build();
        }
    }
}
