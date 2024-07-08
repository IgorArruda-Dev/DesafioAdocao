export type Animal = {
  id: number;
  urlimage: string;
  name: string;
  description: string;
  category: AnimalType;
  birthdate: Date;
  status: string;
};

export enum AnimalType {
  CAT = 'CAT',
  DOG = 'DOG',
}

export enum Status {
  ADOPTED = 'ADOPTED',
  AVAILABLE = 'AVAILABLE',
}

export type Animals = Animal[];
