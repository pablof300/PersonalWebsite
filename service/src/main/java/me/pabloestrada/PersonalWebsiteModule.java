package me.pabloestrada;

import com.google.inject.AbstractModule;
import me.pabloestrada.api.PersonalWebsiteService;
import me.pabloestrada.api.impl.PersonalWebsiteServiceImpl;

public class PersonalWebsiteModule
    extends AbstractModule
{
    @Override
    protected void configure() {
        bind(PersonalWebsiteService.class).to(PersonalWebsiteServiceImpl.class);
    }
}
