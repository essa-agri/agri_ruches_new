import { DeviceStatus } from 'app/shared/model/enumerations/device-status.model';

export interface IDevice {
  id?: string;
  name?: string;
  rucheId?: string | null;
  status?: keyof typeof DeviceStatus;
}

export const defaultValue: Readonly<IDevice> = {};
