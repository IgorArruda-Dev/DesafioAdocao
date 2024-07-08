import { useEffect, useState } from 'react';

export const useScroll = () => {
  const [bottom, setBottom] = useState(false);

  useEffect(() => {
    const onScroll = () => {
      const scrollCheck =
        window.innerHeight + window.scrollY >= document.body.offsetHeight;
      setBottom(scrollCheck);
    };
    document.addEventListener('scroll', onScroll);
    return () => document.removeEventListener('scroll', onScroll);
  }, []);

  return bottom;
};
