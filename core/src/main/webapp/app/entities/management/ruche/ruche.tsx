import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './ruche.reducer';

export const Ruche = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(location, 'id'), location.search));

  const rucheList = useAppSelector(state => state.core.ruche.entities);
  const loading = useAppSelector(state => state.core.ruche.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="ruche-heading" data-cy="RucheHeading">
        <Translate contentKey="coreApp.managementRuche.home.title">Ruches</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="coreApp.managementRuche.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ruche/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="coreApp.managementRuche.home.createLabel">Create new Ruche</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {rucheList && rucheList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="coreApp.managementRuche.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('rucheName')}>
                  <Translate contentKey="coreApp.managementRuche.rucheName">Ruche Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rucheName')} />
                </th>
                <th className="hand" onClick={sort('indentifiant')}>
                  <Translate contentKey="coreApp.managementRuche.indentifiant">Indentifiant</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('indentifiant')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="coreApp.managementRuche.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('rucherId')}>
                  <Translate contentKey="coreApp.managementRuche.rucherId">Rucher Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rucherId')} />
                </th>
                <th className="hand" onClick={sort('deviceId')}>
                  <Translate contentKey="coreApp.managementRuche.deviceId">Device Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deviceId')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {rucheList.map((ruche, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ruche/${ruche.id}`} color="link" size="sm">
                      {ruche.id}
                    </Button>
                  </td>
                  <td>{ruche.rucheName}</td>
                  <td>{ruche.indentifiant}</td>
                  <td>{ruche.description}</td>
                  <td>{ruche.rucherId}</td>
                  <td>{ruche.deviceId}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ruche/${ruche.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ruche/${ruche.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ruche/${ruche.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="coreApp.managementRuche.home.notFound">No Ruches found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Ruche;
