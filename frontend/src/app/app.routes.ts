import { Routes } from '@angular/router';
('admin/reports/users');
('/subscriptions');
export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./home/home').then((m) => m.Home),
    title: 'Home',
  },
  {
    path: 'subscriptions',
    loadComponent: () => import('./sub-post/sub-post').then((m) => m.SubPost),
    title: 'subscriptions',
  },

  {
    path: 'admin/reports/users',
    loadComponent: () => import('./user-report/user-report').then((m) => m.ReportedUsersComponent),
    title: 'reported users',
  },

  {
    path: 'admin/reports/users/:uuid',
    loadComponent: () => import('./all-report-user/all-report-user').then((m) => m.AllReportUser),
    title: 'reported users',
  },

  {
    path: 'admin/reports/post/:postid',
    loadComponent: () => import('./all-report-user/all-report-user').then((m) => m.AllReportUser),
    title: 'reported post',
  },

  {
    path: 'admin/reports/posts',
    loadComponent: () => import('./post-report/post-report').then((m) => m.PostReport),
    title: 'reported post',
  },
  {
    path: 'comments',
    loadComponent: () => import('./comments/comments').then((m) => m.CommentsComponent),
    title: 'comments',
  },
  {
    path: 'admin/users',
    loadComponent: () => import('./users/users').then((m) => m.Users),
    title: 'users',
  },
  {
    path: 'profile/:uuid',
    loadComponent: () => import('./profile/profile').then((m) => m.Profile),
    title: 'profile',
  },
  {
    path: 'search',
    loadComponent: () => import('./search/search').then((m) => m.Search),
    title: 'search',
  },

  {
    path: 'admin',
    loadComponent: () => import('./dashboard/dashboard').then((m) => m.Dashboard),
    title: 'dash Boroad',
  },
  {
    path: 'notifications',
    loadComponent: () => import('./notifications/notifications').then((m) => m.Notifications),
    title: 'notifications',
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
    path: 'update',
    loadComponent: () => import('./update-post/update-post').then((m) => m.UpdatePost),
    title: 'update post',
  },
  {
    path: 'login',
    loadComponent: () => import('./login/login').then((m) => m.Login),
    title: 'login',
  },
  {
    path: '**',
    loadComponent: () => import('./not-fount/not-fount').then((m) => m.NotFount),
    title: 'not found',
  },
];
