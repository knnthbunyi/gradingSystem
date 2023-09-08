export interface ISubject {
  id?: number;
  name?: string | null;
  code?: string | null;
}

export const defaultValue: Readonly<ISubject> = {};
