import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDevice } from 'app/shared/model/sensoring/device.model';
import { DeviceStatus } from 'app/shared/model/enumerations/device-status.model';
import { getEntity, updateEntity, createEntity, reset } from './device.reducer';

export const DeviceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const deviceEntity = useAppSelector(state => state.core.device.entity);
  const loading = useAppSelector(state => state.core.device.loading);
  const updating = useAppSelector(state => state.core.device.updating);
  const updateSuccess = useAppSelector(state => state.core.device.updateSuccess);
  const deviceStatusValues = Object.keys(DeviceStatus);

  const handleClose = () => {
    navigate('/device');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...deviceEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          status: 'OFFLINE',
          ...deviceEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="coreApp.sensoringDevice.home.createOrEditLabel" data-cy="DeviceCreateUpdateHeading">
            <Translate contentKey="coreApp.sensoringDevice.home.createOrEditLabel">Create or edit a Device</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="device-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('coreApp.sensoringDevice.name')}
                id="device-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coreApp.sensoringDevice.rucheId')}
                id="device-rucheId"
                name="rucheId"
                data-cy="rucheId"
                type="text"
              />
              <ValidatedField
                label={translate('coreApp.sensoringDevice.status')}
                id="device-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {deviceStatusValues.map(deviceStatus => (
                  <option value={deviceStatus} key={deviceStatus}>
                    {translate('coreApp.DeviceStatus.' + deviceStatus)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/device" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DeviceUpdate;
