package com.agri40.filahati.management.web.rest;

import com.agri40.filahati.management.domain.Ruche;
import com.agri40.filahati.management.repository.RucheRepository;
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
 * REST controller for managing {@link com.agri40.filahati.management.domain.Ruche}.
 */
@RestController
@RequestMapping("/api")
public class RucheResource {

    private final Logger log = LoggerFactory.getLogger(RucheResource.class);

    private static final String ENTITY_NAME = "managementRuche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RucheService rucheService;
    private final RucherService rucherService;

    private final RucheRepository rucheRepository;

    public RucheResource(RucheService rucheService, RucherService rucherService, RucheRepository rucheRepository) {
        this.rucheService = rucheService;
        this.rucherService = rucherService;
        this.rucheRepository = rucheRepository;
    }

    /**
     * {@code POST  /ruches} : Create a new ruche.
     *
     * @param ruche the ruche to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ruche, or with status {@code 400 (Bad Request)} if the ruche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ruches")
    public ResponseEntity<Ruche> createRuche(@Valid @RequestBody Ruche ruche) throws URISyntaxException {
        log.debug("REST request to save Ruche : {}", ruche);
        if (ruche.getId() != null) {
            throw new BadRequestAlertException("A new ruche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ruche.setDeviceId("R1"); // one device
        Ruche result = rucheService.save(ruche);

        if (result.getRucherId() != null) {
            rucherService.addRucheToRucher(result.getRucherId(), result.getId());
        }
        return ResponseEntity
            .created(new URI("/api/ruches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ruches/:id} : Updates an existing ruche.
     *
     * @param id the id of the ruche to save.
     * @param ruche the ruche to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ruche,
     * or with status {@code 400 (Bad Request)} if the ruche is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ruche couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ruches/{id}")
    public ResponseEntity<Ruche> updateRuche(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Ruche ruche
    ) throws URISyntaxException {
        log.debug("REST request to update Ruche : {}, {}", id, ruche);
        if (ruche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ruche.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rucheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        //

        Optional<Ruche> optionalOldRuche = rucheService.findOne(id);
        if (optionalOldRuche.isPresent()) {
            Ruche oldRuche = optionalOldRuche.get();
            rucherService.deleteRucheInListRuches(oldRuche.getRucherId(), id);
        }
        ruche.setDeviceId("R1");
        Ruche result = rucheService.update(ruche);
        if (ruche.getRucherId() != null) {
            rucherService.addRucheToRucher(ruche.getRucherId(), id);
        }

        //

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ruche.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /ruches/:id} : Partial updates given fields of an existing ruche, field will ignore if it is null
     *
     * @param id the id of the ruche to save.
     * @param ruche the ruche to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ruche,
     * or with status {@code 400 (Bad Request)} if the ruche is not valid,
     * or with status {@code 404 (Not Found)} if the ruche is not found,
     * or with status {@code 500 (Internal Server Error)} if the ruche couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ruches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Ruche> partialUpdateRuche(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Ruche ruche
    ) throws URISyntaxException {
        log.debug("REST request to partial update Ruche partially : {}, {}", id, ruche);
        if (ruche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ruche.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rucheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Ruche> result = rucheService.partialUpdate(ruche);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ruche.getId()));
    }

    /**
     * {@code GET  /ruches} : get all the ruches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ruches in body.
     */
    @GetMapping("/ruches")
    public List<Ruche> getAllRuches() {
        log.debug("REST request to get all Ruches");
        return rucheService.findAll();
    }

    /**
     * {@code GET  /ruches/:id} : get the "id" ruche.
     *
     * @param id the id of the ruche to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ruche, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ruches/{id}")
    public ResponseEntity<Ruche> getRuche(@PathVariable String id) {
        log.debug("REST request to get Ruche : {}", id);
        Optional<Ruche> ruche = rucheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ruche);
    }

    /**
     * {@code DELETE  /ruches/:id} : delete the "id" ruche.
     *
     * @param id the id of the ruche to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ruches/{id}")
    public ResponseEntity<Void> deleteRuche(@PathVariable String id) {
        log.debug("REST request to delete Ruche : {}", id);

        Optional<Ruche> optionalRuche = rucheService.findOne(id);
        if (optionalRuche.isPresent()) {
            Ruche ruche = optionalRuche.get();
            rucherService.deleteRucheInListRuches(ruche.getRucherId(), id);
        }
        rucheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @DeleteMapping("/ruches/ruchesByRucherId/{rucher_id}")
    public ResponseEntity<Void> deleteByRucherId(@PathVariable String rucher_id) {
        log.debug("REST request to delete Ruche : {}", rucher_id);

        /* Optional<Ruche> optionalRuche = rucheService.findOne(id);
        if(optionalRuche.isPresent()){
            Ruche ruche = optionalRuche.get() ;
            rucherService.deleteRucheInListRuches(ruche.getRucherId(), id);
        }*/
        rucheService.deleteByRucherId(rucher_id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, rucher_id))
            .build();
    }

    @GetMapping("/ruchesByRucherId/{rucher_id}")
    public List<Ruche> findAllByRucherId(@PathVariable String rucher_id) {
        log.debug("REST request to get Ruche : {}", rucher_id);
        return rucheService.findByRucherId(rucher_id);
    }
}
