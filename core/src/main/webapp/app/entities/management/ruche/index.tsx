import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Ruche from './ruche';
import RucheDetail from './ruche-detail';
import RucheUpdate from './ruche-update';
import RucheDeleteDialog from './ruche-delete-dialog';

const RucheRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Ruche />} />
    <Route path="new" element={<RucheUpdate />} />
    <Route path=":id">
      <Route index element={<RucheDetail />} />
      <Route path="edit" element={<RucheUpdate />} />
      <Route path="delete" element={<RucheDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RucheRoutes;
