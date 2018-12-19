class ApplicationController < ActionController::Base

  helper_method :current_admin, :logged_in?

  def current_admin
    @current_admin ||= Admin.find(session[:admin_id]) if session[:admin_id]
  end

  def logged_in?
    !!current_admin
  end

  def require_admin
    if !logged_in?
      flash[:error] = "You must be logged in to perform that action"
      redirect_to root_path
    end
  end
end
