package me.pabloestrada.core.personalwebsite;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.pabloestrada.core.CoreServiceConstants;
import me.pabloestrada.core.personalwebsite.websiteinfo.BaseWebsiteInfo;
import me.pabloestrada.core.personalwebsite.projects.ProjectInfo;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.model.Filters;

public final class WebsiteInfoDAO
{
    private final Gson gson;

    private final MongoCollection<BaseWebsiteInfo> baseWebsiteInfoCollection;
    private final MongoCollection<ProjectInfo> projectInfoCollection;

    @Inject
    public WebsiteInfoDAO(final Gson gson, final ConnectionString connectionString) {
        this.gson = gson;
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .applyConnectionString(connectionString)
                .build();
        final MongoDatabase database = MongoClients.create(settings).getDatabase(CoreServiceConstants.DATABASE_NAME);
        baseWebsiteInfoCollection = database.getCollection(CoreServiceConstants.BASE_WEBSITE_INFO_COLLECTION_NAME, BaseWebsiteInfo.class);
        projectInfoCollection = database.getCollection(CoreServiceConstants.PROJECT_INFO_COLLECTION_NAME, ProjectInfo.class);
    }

    public BaseWebsiteInfo getBaseWebsiteInfo() {
        return Optional.ofNullable(baseWebsiteInfoCollection.find().first())
                .orElseGet(() -> {
                    final BaseWebsiteInfo newBaseWebsiteInfo = new BaseWebsiteInfo();
                    baseWebsiteInfoCollection.insertOne(newBaseWebsiteInfo);
                    return newBaseWebsiteInfo;
                });
    }

    public List<ProjectInfo> getProjects() {
        final List<ProjectInfo> projectInfoList = new ArrayList<>();
        projectInfoCollection.find().iterator().forEachRemaining(projectInfoList::add);
        projectInfoList.sort(Comparator.comparingInt(ProjectInfo::getPriority));
        return projectInfoList;
    }

    public void addProjectInfo(final ProjectInfo projectInfo) {
        projectInfoCollection.insertOne(projectInfo);
    }

    public void updateProjectInfo(final ProjectInfo projectInfo) {
        projectInfoCollection.updateOne(
                Filters.eq("_id", projectInfo.getId()), new Document("$set", getUpdatedDocumentWithoutId(projectInfo)));
    }

    public void updateBaseWebsiteInfo(BaseWebsiteInfo baseWebsiteInfo) {
        baseWebsiteInfoCollection.updateOne(new Document(), new Document("$set", Document.parse(gson.toJson(baseWebsiteInfo))));
    }

    private Document getUpdatedDocumentWithoutId(final Object object) {
        final Document updatedInfo = Document.parse(gson.toJson(object));
        updatedInfo.remove("id");
        return updatedInfo;
    }
}
