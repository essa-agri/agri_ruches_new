import rucher from 'app/entities/management/rucher/rucher.reducer';
import ruche from 'app/entities/management/ruche/ruche.reducer';
import stream from 'app/entities/sensoring/stream/stream.reducer';
import device from 'app/entities/sensoring/device/device.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  rucher,
  ruche,
  stream,
  device,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
