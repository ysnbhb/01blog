export const throttle = (func: Function, wait = 10) => {
  let shouldWait = false;
  let waitArg: [any] | null = null;
  const timeFunc = () => {
    if (!waitArg) shouldWait = false;
    else {
      func(...waitArg);
      waitArg = null;
      setTimeout(timeFunc, wait);
    }
  };
  return (...arg: [any]) => {
    if (shouldWait) {
      waitArg = arg;
      return;
    }
    func(...arg);
    shouldWait = true;
    setTimeout(timeFunc, wait);
  };
};
