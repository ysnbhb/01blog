export function getTimeDifferenceInHours(createdAt: string): string {
  const now = new Date();
  const createdTime = new Date(createdAt);
  const diffInMilliseconds = now.getTime() - createdTime.getTime();
  let diffInHours = Math.floor(diffInMilliseconds / (1000 * 60 * 60));
  if (diffInHours < 1) {
    const diffInMinutes = Math.floor(diffInMilliseconds / (1000 * 60));
    return diffInMinutes < 1 ? 'just now' : diffInMinutes + ' minutes ago';
  }
  if (diffInHours > 24) {
    return Math.floor(diffInMilliseconds / (1000 * 60 * 60 * 24)) + ' days ago';
  }
  return diffInHours + ' hours ago';
}
