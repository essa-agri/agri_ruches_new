package com.agri40.filahati.sensoring.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.agri40.filahati.sensoring.IntegrationTest;
import com.agri40.filahati.sensoring.domain.Stream;
import com.agri40.filahati.sensoring.repository.StreamRepository;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link StreamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StreamResourceIT {

    private static final String DEFAULT_DEVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_ID = "BBBBBBBBBB";

    private static final Map<String, ?> DEFAULT_PARAMS = new HashMap<>();
    private static final Map<String, ?> UPDATED_PARAMS = new HashMap<>();

    private static final Long DEFAULT_CREATED_AT = 1222222L;
    private static final Long UPDATED_CREATED_AT = 1421444L;

    private static final String ENTITY_API_URL = "/api/streams";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private StreamRepository streamRepository;

    @Autowired
    private MockMvc restStreamMockMvc;

    private Stream stream;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stream createEntity() {
        Stream stream = new Stream().deviceId(DEFAULT_DEVICE_ID).params(DEFAULT_PARAMS).createdAt(DEFAULT_CREATED_AT);
        return stream;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stream createUpdatedEntity() {
        Stream stream = new Stream().deviceId(UPDATED_DEVICE_ID).params(UPDATED_PARAMS).createdAt(UPDATED_CREATED_AT);
        return stream;
    }

    @BeforeEach
    public void initTest() {
        streamRepository.deleteAll();
        stream = createEntity();
    }

    @Test
    void createStream() throws Exception {
        int databaseSizeBeforeCreate = streamRepository.findAll().size();
        // Create the Stream
        restStreamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isCreated());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeCreate + 1);
        Stream testStream = streamList.get(streamList.size() - 1);
        assertThat(testStream.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testStream.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testStream.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
    }

    @Test
    void createStreamWithExistingId() throws Exception {
        // Create the Stream with an existing ID
        stream.setId("existing_id");

        int databaseSizeBeforeCreate = streamRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStreamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isBadRequest());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeviceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = streamRepository.findAll().size();
        // set the field null
        stream.setDeviceId(null);

        // Create the Stream, which fails.

        restStreamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isBadRequest());

        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkParamsIsRequired() throws Exception {
        int databaseSizeBeforeTest = streamRepository.findAll().size();
        // set the field null
        stream.setParams(null);

        // Create the Stream, which fails.

        restStreamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isBadRequest());

        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = streamRepository.findAll().size();
        // set the field null
        stream.setCreatedAt(null);

        // Create the Stream, which fails.

        restStreamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isBadRequest());

        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllStreams() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        // Get all the streamList
        restStreamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stream.getId())))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID)))
            .andExpect(jsonPath("$.[*].params").value(hasItem(DEFAULT_PARAMS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT)));
    }

    @Test
    void getStream() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        // Get the stream
        restStreamMockMvc
            .perform(get(ENTITY_API_URL_ID, stream.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stream.getId()))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID))
            .andExpect(jsonPath("$.params").value(DEFAULT_PARAMS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT));
    }

    @Test
    void getNonExistingStream() throws Exception {
        // Get the stream
        restStreamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingStream() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        int databaseSizeBeforeUpdate = streamRepository.findAll().size();

        // Update the stream
        Stream updatedStream = streamRepository.findById(stream.getId()).orElseThrow();
        updatedStream.deviceId(UPDATED_DEVICE_ID).params(UPDATED_PARAMS).createdAt(UPDATED_CREATED_AT);

        restStreamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStream.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStream))
            )
            .andExpect(status().isOk());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
        Stream testStream = streamList.get(streamList.size() - 1);
        assertThat(testStream.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testStream.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testStream.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
    }

    @Test
    void putNonExistingStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stream.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stream))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stream))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateStreamWithPatch() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        int databaseSizeBeforeUpdate = streamRepository.findAll().size();

        // Update the stream using partial update
        Stream partialUpdatedStream = new Stream();
        partialUpdatedStream.setId(stream.getId());

        partialUpdatedStream.deviceId(UPDATED_DEVICE_ID);

        restStreamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStream.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStream))
            )
            .andExpect(status().isOk());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
        Stream testStream = streamList.get(streamList.size() - 1);
        assertThat(testStream.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testStream.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testStream.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
    }

    @Test
    void fullUpdateStreamWithPatch() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        int databaseSizeBeforeUpdate = streamRepository.findAll().size();

        // Update the stream using partial update
        Stream partialUpdatedStream = new Stream();
        partialUpdatedStream.setId(stream.getId());

        partialUpdatedStream.deviceId(UPDATED_DEVICE_ID).params(UPDATED_PARAMS).createdAt(UPDATED_CREATED_AT);

        restStreamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStream.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStream))
            )
            .andExpect(status().isOk());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
        Stream testStream = streamList.get(streamList.size() - 1);
        assertThat(testStream.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testStream.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testStream.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
    }

    @Test
    void patchNonExistingStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stream.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stream))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stream))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamStream() throws Exception {
        int databaseSizeBeforeUpdate = streamRepository.findAll().size();
        stream.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStreamMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(stream)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Stream in the database
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteStream() throws Exception {
        // Initialize the database
        streamRepository.save(stream);

        int databaseSizeBeforeDelete = streamRepository.findAll().size();

        // Delete the stream
        restStreamMockMvc
            .perform(delete(ENTITY_API_URL_ID, stream.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stream> streamList = streamRepository.findAll();
        assertThat(streamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
