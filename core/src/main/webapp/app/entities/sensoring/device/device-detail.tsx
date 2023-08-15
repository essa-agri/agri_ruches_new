import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './device.reducer';

export const DeviceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const deviceEntity = useAppSelector(state => state.core.device.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="deviceDetailsHeading">
          <Translate contentKey="coreApp.sensoringDevice.detail.title">Device</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coreApp.sensoringDevice.name">Name</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.name}</dd>
          <dt>
            <span id="rucheId">
              <Translate contentKey="coreApp.sensoringDevice.rucheId">Ruche Id</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.rucheId}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="coreApp.sensoringDevice.status">Status</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/device" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/device/${deviceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DeviceDetail;
