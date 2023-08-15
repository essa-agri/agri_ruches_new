import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './stream.reducer';

export const StreamDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const streamEntity = useAppSelector(state => state.core.stream.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="streamDetailsHeading">
          <Translate contentKey="coreApp.sensoringStream.detail.title">Stream</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{streamEntity.id}</dd>
          <dt>
            <span id="deviceId">
              <Translate contentKey="coreApp.sensoringStream.deviceId">Device Id</Translate>
            </span>
          </dt>
          <dd>{streamEntity.deviceId}</dd>
          <dt>
            <span id="params">
              <Translate contentKey="coreApp.sensoringStream.params">Params</Translate>
            </span>
          </dt>
          <dd>{streamEntity.params}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="coreApp.sensoringStream.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{streamEntity.createdAt}</dd>
        </dl>
        <Button tag={Link} to="/stream" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/stream/${streamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StreamDetail;
