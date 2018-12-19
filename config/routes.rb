Rails.application.routes.draw do
  resources :messages
  root 'pages#index'
end
