import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Rucher from './rucher';
import RucherDetail from './rucher-detail';
import RucherUpdate from './rucher-update';
import RucherDeleteDialog from './rucher-delete-dialog';

const RucherRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Rucher />} />
    <Route path="new" element={<RucherUpdate />} />
    <Route path=":id">
      <Route index element={<RucherDetail />} />
      <Route path="edit" element={<RucherUpdate />} />
      <Route path="delete" element={<RucherDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RucherRoutes;
