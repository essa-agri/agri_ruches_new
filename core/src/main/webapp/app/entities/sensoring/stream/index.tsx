import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Stream from './stream';
import StreamDetail from './stream-detail';
import StreamUpdate from './stream-update';
import StreamDeleteDialog from './stream-delete-dialog';

const StreamRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Stream />} />
    <Route path="new" element={<StreamUpdate />} />
    <Route path=":id">
      <Route index element={<StreamDetail />} />
      <Route path="edit" element={<StreamUpdate />} />
      <Route path="delete" element={<StreamDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default StreamRoutes;
