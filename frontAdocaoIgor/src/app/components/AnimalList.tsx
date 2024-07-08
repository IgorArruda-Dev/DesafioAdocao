'use client';
import { Animals } from '@/app/types/animal';
import { GetAllAnimals } from '@/app/service/AnimalService';
import { useEffect, useState } from 'react';
import { useScroll } from '@/app/hooks/UseScroll';
import AnimalCard from '@/app/components/AnimalCard';
import { Spinner } from '@/app/components/Spinner';

const itemPerPage: number = 8;

function AnimalList() {
  const isScrolled = useScroll();
  const [hasMoreItems, setHasMoreItems] = useState(true);
  const [animalsList, setAnimalsList] = useState<Animals>([]);
  const [page, setPage] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [retryCount, setRetryCount] = useState(0);

  const loadItems = async () => {
    if (hasMoreItems) {
      try {
        setIsLoading(true);
        const response = await GetAllAnimals(page, itemPerPage);
        const newAnimals = [...animalsList, ...response.items];
        setAnimalsList(newAnimals);
        if (response.total === newAnimals.length) {
          setHasMoreItems(false);
        }
        setIsLoading(false);
      } catch (err) {
        console.log(err);
        if (retryCount != 10) {
          await new Promise((resolve) => setTimeout(resolve, 1000 * 5));
          await loadItems();
        }
      }
    }
  };

  useEffect(() => {
    if (!isLoading) {
      loadItems().catch((err) => console.log(err));
    }
  }, [isScrolled]);

  return (
    <div>
      <div className='grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4'>
        {animalsList.map((animal) => (
          <AnimalCard key={animal.id} animal={animal} />
        ))}
      </div>
      {isLoading && (
        <div className='absolute inset-0 flex items-center justify-center bg-white bg-opacity-20'>
          <Spinner />
        </div>
      )}
    </div>
  );
}

export default AnimalList;
