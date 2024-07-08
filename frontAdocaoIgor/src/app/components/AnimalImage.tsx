import React from 'react';

export function AnimalImage({
  urlImage,
  name,
}: {
  urlImage: string | undefined;
  name: string;
}) {
  if (urlImage == undefined) {
    urlImage = 'default image';
  }
  return (
    <img src={urlImage} className='h-full w-full object-cover' alt={name} />
  );
}
