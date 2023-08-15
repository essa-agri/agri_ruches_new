import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/rucher">
        <Translate contentKey="global.menu.entities.managementRucher" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ruche">
        <Translate contentKey="global.menu.entities.managementRuche" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/stream">
        <Translate contentKey="global.menu.entities.sensoringStream" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/device">
        <Translate contentKey="global.menu.entities.sensoringDevice" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
