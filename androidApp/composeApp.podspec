Pod::Spec.new do |spec|
    spec.name                     = 'androidApp'
    spec.version                  = '1.0.0'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Some description for the Shared Module'
    spec.vendored_frameworks      = 'build/cocoapods/framework/androidApp.framework'
    spec.libraries                = 'c++'
                
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':androidApp',
        'PRODUCT_MODULE_NAME' => 'AndroidApp',
    }
                
    spec.script_phases = [
        {
            :name => 'Build androidApp',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
    spec.resource_bundles = {
        'LibresAndroidApp' => ['build/generated/libres/apple/resources/images/LibresAndroidApp.xcassets']
    }
end