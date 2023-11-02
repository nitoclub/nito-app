import About
import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Hoge().hoge())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
