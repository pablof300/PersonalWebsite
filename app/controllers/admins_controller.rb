class AdminsController < ApplicationController

  before_action :require_admin

  def panel
    @messages = Message.all
  end
end
