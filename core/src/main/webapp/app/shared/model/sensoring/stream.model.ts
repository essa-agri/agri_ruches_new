export interface IStream {
  id?: string;
  deviceId?: string;
  params?: string;
  createdAt?: string;
}

export const defaultValue: Readonly<IStream> = {};
