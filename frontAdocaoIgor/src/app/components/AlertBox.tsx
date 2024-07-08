'use client';
export function AlertBox({ message }: { message: string }) {
  return (
    <div
      className={`fixed right-8 top-8 z-50 rounded border border-red-400 bg-red-100 px-4 py-3 text-red-700`}
      role='alert'
    >
      <strong className='font-bold'>Error!</strong>
      <span className='block sm:inline'> {message}</span>
    </div>
  );
}
