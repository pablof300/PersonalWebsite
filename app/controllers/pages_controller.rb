class PagesController < ApplicationController
  def index
    @message = Message.new
  end
end
