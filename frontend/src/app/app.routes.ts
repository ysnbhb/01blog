import { Routes } from '@angular/router';
export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./home/home').then((m) => m.Home),
    title: 'Home',
  },
  {
    path: 'comments',
    loadComponent: () => import('./comments/comments').then((m) => m.CommentsComponent),
    title: 'comments',
  },
  {
    path: 'create-post',
    loadComponent: () => import('./create-post/create-post').then((m) => m.CreatePost),
    title: 'create post',
  },
  {
    path: 'post',
    loadComponent: () => import('./post-view/post-view').then((m) => m.PostView),
    title: 'post',
  },
  {
    path: 'register',
    loadComponent: () => import('./register/register').then((m) => m.Register),
    title: 'regiter',
  },
  {
    path: 'login',
    loadComponent: () => import('./login/login').then((m) => m.Login),
    title: 'login',
  },
];
