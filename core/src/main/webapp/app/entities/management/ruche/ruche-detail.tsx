import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ruche.reducer';

export const RucheDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rucheEntity = useAppSelector(state => state.core.ruche.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rucheDetailsHeading">
          <Translate contentKey="coreApp.managementRuche.detail.title">Ruche</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.id}</dd>
          <dt>
            <span id="rucheName">
              <Translate contentKey="coreApp.managementRuche.rucheName">Ruche Name</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.rucheName}</dd>
          <dt>
            <span id="indentifiant">
              <Translate contentKey="coreApp.managementRuche.indentifiant">Indentifiant</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.indentifiant}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="coreApp.managementRuche.description">Description</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.description}</dd>
          <dt>
            <span id="rucherId">
              <Translate contentKey="coreApp.managementRuche.rucherId">Rucher Id</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.rucherId}</dd>
          <dt>
            <span id="deviceId">
              <Translate contentKey="coreApp.managementRuche.deviceId">Device Id</Translate>
            </span>
          </dt>
          <dd>{rucheEntity.deviceId}</dd>
        </dl>
        <Button tag={Link} to="/ruche" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ruche/${rucheEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RucheDetail;
